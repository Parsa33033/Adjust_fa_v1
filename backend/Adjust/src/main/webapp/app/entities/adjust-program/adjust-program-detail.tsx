import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-program.reducer';
import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustProgramDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustProgramDetail = (props: IAdjustProgramDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustProgramEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustProgram.detail.title">AdjustProgram</Translate> [<b>{adjustProgramEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="createdAt">
              <Translate contentKey="adjustApp.adjustProgram.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {adjustProgramEntity.createdAt ? (
              <TextFormat value={adjustProgramEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="expirationDate">
              <Translate contentKey="adjustApp.adjustProgram.expirationDate">Expiration Date</Translate>
            </span>
          </dt>
          <dd>
            {adjustProgramEntity.expirationDate ? (
              <TextFormat value={adjustProgramEntity.expirationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="designed">
              <Translate contentKey="adjustApp.adjustProgram.designed">Designed</Translate>
            </span>
          </dt>
          <dd>{adjustProgramEntity.designed ? 'true' : 'false'}</dd>
          <dt>
            <span id="done">
              <Translate contentKey="adjustApp.adjustProgram.done">Done</Translate>
            </span>
          </dt>
          <dd>{adjustProgramEntity.done ? 'true' : 'false'}</dd>
          <dt>
            <span id="paid">
              <Translate contentKey="adjustApp.adjustProgram.paid">Paid</Translate>
            </span>
          </dt>
          <dd>{adjustProgramEntity.paid ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="adjustApp.adjustProgram.bodyCompostion">Body Compostion</Translate>
          </dt>
          <dd>{adjustProgramEntity.bodyCompostionId ? adjustProgramEntity.bodyCompostionId : ''}</dd>
          <dt>
            <Translate contentKey="adjustApp.adjustProgram.fitnessProgram">Fitness Program</Translate>
          </dt>
          <dd>{adjustProgramEntity.fitnessProgramId ? adjustProgramEntity.fitnessProgramId : ''}</dd>
          <dt>
            <Translate contentKey="adjustApp.adjustProgram.nutritionProgram">Nutrition Program</Translate>
          </dt>
          <dd>{adjustProgramEntity.nutritionProgramId ? adjustProgramEntity.nutritionProgramId : ''}</dd>
          <dt>
            <Translate contentKey="adjustApp.adjustProgram.client">Client</Translate>
          </dt>
          <dd>{adjustProgramEntity.clientId ? adjustProgramEntity.clientId : ''}</dd>
          <dt>
            <Translate contentKey="adjustApp.adjustProgram.specialist">Specialist</Translate>
          </dt>
          <dd>{adjustProgramEntity.specialistId ? adjustProgramEntity.specialistId : ''}</dd>
        </dl>
        <Button tag={Link} to="/adjust-program" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-program/${adjustProgramEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustProgram }: IRootState) => ({
  adjustProgramEntity: adjustProgram.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustProgramDetail);
