import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-tokens.reducer';
import { IAdjustTokens } from 'app/shared/model/adjust-tokens.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustTokensDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustTokensDetail = (props: IAdjustTokensDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustTokensEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustTokens.detail.title">AdjustTokens</Translate> [<b>{adjustTokensEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.adjustTokens.name">Name</Translate>
            </span>
          </dt>
          <dd>{adjustTokensEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.adjustTokens.description">Description</Translate>
            </span>
          </dt>
          <dd>{adjustTokensEntity.description}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="adjustApp.adjustTokens.token">Token</Translate>
            </span>
          </dt>
          <dd>{adjustTokensEntity.token}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="adjustApp.adjustTokens.price">Price</Translate>
            </span>
          </dt>
          <dd>{adjustTokensEntity.price}</dd>
          <dt>
            <span id="image">
              <Translate contentKey="adjustApp.adjustTokens.image">Image</Translate>
            </span>
          </dt>
          <dd>
            {adjustTokensEntity.image ? (
              <div>
                {adjustTokensEntity.imageContentType ? (
                  <a onClick={openFile(adjustTokensEntity.imageContentType, adjustTokensEntity.image)}>
                    <img
                      src={`data:${adjustTokensEntity.imageContentType};base64,${adjustTokensEntity.image}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {adjustTokensEntity.imageContentType}, {byteSize(adjustTokensEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/adjust-tokens" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-tokens/${adjustTokensEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustTokens }: IRootState) => ({
  adjustTokensEntity: adjustTokens.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustTokensDetail);
