import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './adjust-price.reducer';
import { IAdjustPrice } from 'app/shared/model/adjust-price.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdjustPriceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustPriceUpdate = (props: IAdjustPriceUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { adjustPriceEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/adjust-price');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...adjustPriceEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.adjustPrice.home.createOrEditLabel">
            <Translate contentKey="adjustApp.adjustPrice.home.createOrEditLabel">Create or edit a AdjustPrice</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adjustPriceEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="adjust-price-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="adjust-price-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="adjust-price-name">
                  <Translate contentKey="adjustApp.adjustPrice.name">Name</Translate>
                </Label>
                <AvField id="adjust-price-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="tokenLabel" for="adjust-price-token">
                  <Translate contentKey="adjustApp.adjustPrice.token">Token</Translate>
                </Label>
                <AvField id="adjust-price-token" type="string" className="form-control" name="token" />
              </AvGroup>
              <AvGroup>
                <Label id="priceLabel" for="adjust-price-price">
                  <Translate contentKey="adjustApp.adjustPrice.price">Price</Translate>
                </Label>
                <AvField id="adjust-price-price" type="string" className="form-control" name="price" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/adjust-price" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  adjustPriceEntity: storeState.adjustPrice.entity,
  loading: storeState.adjustPrice.loading,
  updating: storeState.adjustPrice.updating,
  updateSuccess: storeState.adjustPrice.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustPriceUpdate);
