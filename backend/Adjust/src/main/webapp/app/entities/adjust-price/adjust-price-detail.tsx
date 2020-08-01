import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-price.reducer';
import { IAdjustPrice } from 'app/shared/model/adjust-price.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustPriceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustPriceDetail = (props: IAdjustPriceDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustPriceEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustPrice.detail.title">AdjustPrice</Translate> [<b>{adjustPriceEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.adjustPrice.name">Name</Translate>
            </span>
          </dt>
          <dd>{adjustPriceEntity.name}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="adjustApp.adjustPrice.token">Token</Translate>
            </span>
          </dt>
          <dd>{adjustPriceEntity.token}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="adjustApp.adjustPrice.price">Price</Translate>
            </span>
          </dt>
          <dd>{adjustPriceEntity.price}</dd>
        </dl>
        <Button tag={Link} to="/adjust-price" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-price/${adjustPriceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustPrice }: IRootState) => ({
  adjustPriceEntity: adjustPrice.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustPriceDetail);
